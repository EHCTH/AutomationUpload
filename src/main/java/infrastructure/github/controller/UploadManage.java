package infrastructure.github.controller;

import domain.algorithm.problem.ProblemFile;

import java.io.IOException;
import java.util.List;

public interface UploadManage {
    void uploadFile(List<ProblemFile> problemFiles) throws IOException;
}
