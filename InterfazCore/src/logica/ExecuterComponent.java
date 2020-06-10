/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dark
 */
public class ExecuterComponent {

    private BufferedReader buffer_error;
    private BufferedReader buffer_result;
    private int valid;

    public ExecuterComponent() {
        this.buffer_error = null;
        this.buffer_result = null;
        this.valid = 0;
    }

    public ResponseExecuter executeJar(String file_path, List<String> args, int num_column) throws IOException {
        // Create run arguments for the

        final List<String> actualArgs = new ArrayList<String>();
        actualArgs.add(0, "java");
        actualArgs.add(1, "-jar");
        actualArgs.add(2, file_path);
        actualArgs.addAll(args);
        String errors = "Error al Ejecutar el componente";
        String line;
                String output = "";
        try {
            final Runtime runtine = Runtime.getRuntime();
            final Process process = runtine.exec(actualArgs.toArray(new String[0]));
            this.buffer_error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            this.buffer_result = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Wait for the application to Finish
            process.waitFor();
            this.valid = process.exitValue();
            if (this.valid == 0) {
                ArrayList<String> recovery_data = new ArrayList<>();
                try {
                    while ((line = this.buffer_result.readLine()) != null) {
                        recovery_data.add(line);
                    }
                    Object[][] data = new Object[recovery_data.size()][num_column];
                    for(int i=0; i < recovery_data.size();i++){
                        String[] recovery_line_data = recovery_data.get(i).split(";");
                        jump_recovery:
                        for(int k=0; k<recovery_line_data.length; k++){
                            if(k < num_column){
                                data[i][k]=recovery_line_data[k];
                            }
                        }
                    }
                    return new ResponseExecuter(true, "Ejecucion del componente.", data);
                } catch (final IOException e) {
                }
                try {
                    this.buffer_error.close();
                    this.buffer_result.close();
                } catch (final IOException e) {
                }
            }else{
                try {
                    while ((line = this.buffer_error.readLine()) != null) {
                        errors = errors + "\n" + line;
                    }
                } catch (final IOException e) {
                }
            }

        } catch (final IOException | InterruptedException e) {
            //throw new IOException("Error al Ejecutar el componente");
        }
        Object[][] data = new Object[0][num_column];
        return new ResponseExecuter(false, errors, data);
    }

    public String getExecutionLog() {
        String error = "";
        String line;
        try {
            while ((line = this.buffer_error.readLine()) != null) {
                error = error + "\n" + line;
            }
        } catch (final IOException e) {
        }
        String output = "";
        try {
            while ((line = this.buffer_result.readLine()) != null) {
                output = output + "\n" + line;
            }
        } catch (final IOException e) {
        }
        try {
            this.buffer_error.close();
            this.buffer_result.close();
        } catch (final IOException e) {
        }
        return "exitVal: " + this.valid + ", error: " + error + ", output: " + output;
    }

    public class ResponseExecuter {

        private boolean valido;
        private String mensage;
        private Object[][] data;

        @Override
        public String toString() {
            return "ResponseExecuter{" + "valido=" + valido + ", mensage=" + mensage + ", data=" + data + '}';
        }

        public ResponseExecuter(boolean valido, String mensage, Object[][] data) {
            this.valido = valido;
            this.mensage = mensage;
            this.data = data;
        }

        public ResponseExecuter(boolean valido, String mensage) {
            this.valido = valido;
            this.mensage = mensage;
            this.data = new Object[0][2];
        }

        public ResponseExecuter() {
            this.valido = false;
            this.mensage = "";
            this.data = new Object[0][2];
        }

        public void setValido(boolean valido) {
            this.valido = valido;
        }

        public void setMensage(String mensage) {
            this.mensage = mensage;
        }

        public void setData(Object[][] data) {
            this.data = data;
        }

        public boolean isValido() {
            return valido;
        }

        public String getMensage() {
            return mensage;
        }

        public Object[][] getData() {
            return data;
        }

    }
}
