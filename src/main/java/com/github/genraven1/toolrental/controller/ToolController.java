package com.github.genraven1.toolrental.controller;

import com.github.genraven1.toolrental.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(final ToolService toolService) {
        this.toolService = toolService;
    }
}
