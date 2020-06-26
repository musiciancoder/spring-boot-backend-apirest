package info.ad80.spring.boot.backend.apirest.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImp implements IUploadFileService {

	private static final Logger log = LoggerFactory.getLogger(UploadFileServiceImp.class);

	private static final String DIRECTORIO_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {

		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString()); // para informar explicitamente la ruta

		Resource recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("anonimo.png").toAbsolutePath();

			log.error("Error; no se pudo cargar la imagen " + nombreFoto);
		}

		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString()  + archivo.getOriginalFilename().replace(" ", "_"); // nombreArchivo es el nombre del archivo en la app,
		// con getOriginalFileName() obtenemos el nombre que
		// le da el usuario. UUID.randomUUID().toString() son caracteres al azar
		Path rutaArchivo = getPath(nombreArchivo);

		log.info(rutaArchivo.toString()); //para informar explicitamente la ruta en el log de Spring (en la consola cuando parte la aplicacion)


		Files.copy(archivo.getInputStream(), rutaArchivo);

		return nombreArchivo;
	}

	

	@Override
	public boolean eliminar(String nombreFoto) {

		if (nombreFoto != null && nombreFoto.length()>0) {
			Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists()&&archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		
		return Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
	}

}
