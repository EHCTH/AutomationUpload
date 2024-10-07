package infrastructure.github.controller;

import domain.algorithm.problem.ProblemFile;
import infrastructure.github.service.FileUploaderService;

import java.io.IOException;
import java.util.List;

public class GithubController implements UploadManage {
    private final FileUploaderService fileUploaderService;
    public GithubController(FileUploaderService fileUploaderService) {
        this.fileUploaderService = fileUploaderService;
    }
    public void uploadFile(List<ProblemFile> problemFiles) throws IOException {
        fileUploaderService.uploadFiles(problemFiles);
    }

}
