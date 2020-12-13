package ${pPackage};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("${pPackage}.dao")
public class ${ClassName}Application {
    public static void main(String[] args) {
        SpringApplication.run(${ClassName}Application.class, args);
    }
}
