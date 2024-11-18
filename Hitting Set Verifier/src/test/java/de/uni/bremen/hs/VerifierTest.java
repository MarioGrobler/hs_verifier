package de.uni.bremen.hs;

import de.uni.bremen.hs.io.Importer;
import de.uni.bremen.hs.model.HyperGraph;
import de.uni.bremen.hs.model.Solution;
import de.uni.bremen.hs.verifier.HSVerifier;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class VerifierTest {

    final static String TEST_DIR = "testset";

    @Test
    void testAll() {
        Stream.of(Objects.requireNonNull(new File(TEST_DIR).listFiles()))
                .filter(file -> !file.isDirectory())
                .forEach(file -> {
                    if(file.getName().endsWith(".hgr")) {
                        try {
                            Importer importer = new Importer();
                            HyperGraph g = importer.readHyperGraph(file.getPath());
                            Solution sol = importer.readSolution(file.getPath().replace(".hgr", ".sol"));

                            HSVerifier verifier = new HSVerifier();
                            assertTrue(verifier.verify(g, sol));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}