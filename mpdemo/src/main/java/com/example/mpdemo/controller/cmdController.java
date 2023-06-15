package com.example.mpdemo.controller;
import com.example.mpdemo.service.CmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


public class cmdController {
    @Autowired
    private CmdService cmdService;

    @PostMapping("/cmd")
    public void face() throws IOException, InterruptedException {
        cmdService.main();
    }


}
