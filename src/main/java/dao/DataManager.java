package dao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;

import static view.MainMenu.logger;

public class DataManager<T> {

    private String fileName;
    private static final String ROOT_PATH = System.getProperty("user.dir") + "/src/main/java/data/";
    private static final String EXTENSION = ".data";
    private List<T> dataList;

    private File file;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public DataManager(String newFileName, List<T> dataList) {

        this.fileName = newFileName + EXTENSION;

        this.file = new File(ROOT_PATH + fileName);

        //Creamos el archivo DATA
        if (!Files.exists(Path.of(ROOT_PATH + "/" + fileName))) {
            try {
                File file = new File(ROOT_PATH + fileName);
                if (file.createNewFile()) logger.log(Level.INFO, "Creado archivo " + fileName);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error creando el archivo " + fileName + " | " + e);
            }
        }

        //Definimos tipo de lista
        this.dataList = dataList;

        //Primera Carga
        if (loadFile()) {
            logger.log(Level.INFO, "Cargado " + fileName + " con exito");
        } else {
            logger.log(Level.SEVERE, "Error cargando " + fileName);
        }

    }

    //Metodos CRUD
    public T save(T t) {
        if (dataList.add(t)) {
            if (persistFile()) return t;
        }
        logger.log(Level.SEVERE, "Error en metodo save de " + fileName);
        return null;
    }

    public T delete(T t) {
        if (dataList.remove(t)) {
            if (persistFile()) return t;
        }
        logger.log(Level.SEVERE, "Error en metodo delete de " + fileName);
        return null;
    }

    public T update(T t) {
        if (dataList.remove(t)) {
            return save(t);
        }
        logger.log(Level.SEVERE, "Error en metodo update de " + fileName);
        return null;
    }

    public List<T> getList() {
        return dataList;
    }

    //Metodos relacionados con los archivos, lectura y escritura
    private boolean loadFile() {

        try {
            this.fileInputStream = new FileInputStream(file);
            this.objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (FileNotFoundException fnfe) {
            logger.log(Level.SEVERE, "Error instanciando el FIS/OIS de" + fileName + " | " + fnfe);
        } catch (IOException e) {
        }

        try {
            if (fileInputStream.available() > 0) {
                while (fileInputStream.available() > 0) dataList.add((T) objectInputStream.readObject());
            }
            return true;
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Error de lectura en " + fileName + " | " + ioe);
            return false;
        } catch (ClassNotFoundException cnfe) {
            logger.log(Level.SEVERE, "Error de lectura en " + fileName + " | " + cnfe);
            return false;
        }

    }

    private boolean persistFile() {

        try {
            this.fileOutputStream = new FileOutputStream(file);
            this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error instanciando el FOS/OOS de" + fileName + " | " + e);
        }

        dataList.forEach((t -> {
            try {
                objectOutputStream.writeObject(t);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error de escritura en " + fileName + " | " + e);
            }
        }));

        try {
            fileOutputStream.flush();
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Error en el flush de " + fileName + " | " + ioe);
        }

        return true;
    }

}
