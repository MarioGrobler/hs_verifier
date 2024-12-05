package de.uni.bremen.hs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import de.uni.bremen.hs.io.Importer;
import de.uni.bremen.hs.model.HyperGraph;
import de.uni.bremen.hs.model.Solution;
import de.uni.bremen.hs.verifier.HSVerifier;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.stream.Stream;

class VerifierTest {

  final static String TEST_DIR = "testset";

  @Test
  void testAll() throws URISyntaxException {
    var testDir = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(TEST_DIR)).toURI());

    Stream.of(Objects.requireNonNull(testDir.listFiles()))
        .filter(file -> !file.isDirectory())
        .forEach(file -> {
          if (file.getName().endsWith(".hgr")) {
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