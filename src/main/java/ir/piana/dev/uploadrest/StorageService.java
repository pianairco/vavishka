package ir.piana.dev.uploadrest;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    GroupProperties getGroupProperties(String group);

    String store(MultipartFile file, String group);

    String store(MultipartFile file, String group, String rotation);

    String store(MultipartFile file, String group, String rotation, Integer width, Integer height);

    String store(String file, String group);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
