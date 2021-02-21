package ru.otus.jdbcprj;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class EmbedDbInitializer {

    private MongodExecutable mongodExecutable;

    @BeforeEach
    public void beforeEach() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(27017, false))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);

        try {
            mongodExecutable.start();
        } catch (Exception e) {
            closeMongoExecutable();
        }
    }

    @AfterEach
    private void afterEach() {
        closeMongoExecutable();
    }

    private void closeMongoExecutable() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
}
