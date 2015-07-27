package com.example.eric.banheirobomv1;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by eric on 21/07/2015.
 */
public class UsuarioDAO {

    private static final String URL = "http://192.168.25.9:8080/WebService_android_eric/services/UsuarioDAO?wsdl";
    private static final String NAMESPACE = "http://WSapp.com.br";
    private static final String INSERIR = "inserirUsuario";
    private static final String EXCLUIR = "excluirUsuario";
    private static final String ATUALIZAR = "atualizarUsuario";
    private static final String BUSCAR_TODOS = "buscarTodosUsuarios";
    private static final String BUSCAR_POR_ID = "buscarUsuarioporID";



        public boolean inserirUsuario (Usuario usuario){
            SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);
            SoapObject usr = new SoapObject(NAMESPACE, "usuario");

            usr.addProperty("id",usuario.getId());
            usr.addProperty("email", usuario.getEmail());
            usr.addProperty("nome", usuario.getNome());
            usr.addProperty("senha", usuario.getSenha());

            inserirUsuario.addSoapObject(usr);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(inserirUsuario);

            envelope.implicitTypes = true;

            HttpTransportSE http = new HttpTransportSE(URL);
            try {
                http.call("urn:"+INSERIR, envelope);

                SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
                return Boolean.parseBoolean(resposta.toString());

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }


        }

        public boolean atualizarUsuario (Usuario usuario){

            SoapObject atualizarUsuario = new SoapObject(NAMESPACE, ATUALIZAR);
            SoapObject usr = new SoapObject(NAMESPACE, "usuario");

            usr.addProperty("id",usuario.getId());
            usr.addProperty("email", usuario.getEmail());
            usr.addProperty("nome", usuario.getNome());
            usr.addProperty("senha", usuario.getSenha());

            atualizarUsuario.addSoapObject(usr);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(atualizarUsuario);

            envelope.implicitTypes = true;

            HttpTransportSE http = new HttpTransportSE(URL);
            try {
                http.call("urn:"+ATUALIZAR, envelope);

                SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
                return Boolean.parseBoolean(resposta.toString());

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }



        }


        public boolean excluirUsuario (Usuario usuario){

            return true;
        }

        public ArrayList<Usuario> buscarTodosUsuarios(){
            ArrayList<Usuario> lista = new ArrayList<Usuario>();

            SoapObject buscarUsuario = new SoapObject(NAMESPACE, BUSCAR_TODOS);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(buscarUsuario);

            envelope.implicitTypes = true;

            HttpTransportSE http = new HttpTransportSE(URL);
            try {
                http.call("urn:"+BUSCAR_TODOS, envelope);

                Vector<SoapObject> resposta = (Vector<SoapObject>)envelope.getResponse();

                for(SoapObject soapObject:resposta){

                    Usuario usr = new Usuario();
                    usr.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                    usr.setEmail(soapObject.getProperty("email").toString());
                    usr.setNome(soapObject.getProperty("nome").toString());
                    usr.setSenha(soapObject.getProperty("senha").toString());
                    lista.add(usr);

                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return lista;
        }

        public Usuario buscarUsuarioporID(int id){
            Usuario usr = null;
            SoapObject buscarUsuario = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
            buscarUsuario.addProperty("id", id);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(buscarUsuario);

            envelope.implicitTypes = true;

            HttpTransportSE http = new HttpTransportSE(URL);
            try {
                http.call("urn:"+BUSCAR_POR_ID, envelope);

              SoapObject resposta = (SoapObject)envelope.getResponse();


                usr.setId(Integer.parseInt(resposta.getProperty("id").toString()));
                usr.setEmail(resposta.getProperty("email").toString());
                usr.setNome(resposta.getProperty("nome").toString());
                usr.setSenha(resposta.getProperty("senha").toString());

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }




            return usr;
        }
        public boolean excluirUsuario (int id){
            return excluirUsuario(new Usuario(id," "," "," "));

        }




}
