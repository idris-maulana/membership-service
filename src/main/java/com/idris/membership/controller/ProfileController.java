package com.idris.membership.controller;

import com.idris.membership.common.CommonResponse;
import com.idris.membership.payloads.request.UpdateProfileRequest;
import com.idris.membership.service.ProfileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok(profileService.getProfile(token));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(new CommonResponse(500, ex.getMessage(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, @RequestBody @Valid UpdateProfileRequest request) {
        try {
            return ResponseEntity.ok(profileService.updateProfile(token, request));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(new CommonResponse(500, ex.getMessage(), null));
        }
    }

    @PutMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateImageProfile(@RequestHeader("Authorization") String token, @RequestPart("file") MultipartFile request) {
        try {
            return ResponseEntity.ok(profileService.updateImageProfile(token, request));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(new CommonResponse(500, ex.getMessage(), null));
        }
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<?> getImageProfile(HttpServletResponse response, @RequestHeader("Authorization") String token, @PathVariable("filename") String filename) {
        try {
            return ResponseEntity.ok().header("Content-Type", "image/" + FilenameUtils.getExtension(filename)).body(profileService.getImageProfile(token, filename));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(new CommonResponse(500, ex.getMessage(), null));
        }
    }
}
