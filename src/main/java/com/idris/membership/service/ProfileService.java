package com.idris.membership.service;

import com.idris.membership.common.CommonResponse;
import com.idris.membership.common.StringHelper;
import com.idris.membership.model.Image;
import com.idris.membership.model.User;
import com.idris.membership.payloads.request.UpdateProfileRequest;
import com.idris.membership.payloads.response.ProfileResponse;
import com.idris.membership.repository.ImageRepository;
import com.idris.membership.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.LockModeType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Transactional
    public CommonResponse<?> getProfile(String token) {
        User user = jwtService.verifyToken(token);

        ProfileResponse response = new ProfileResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setProfileImage(user.getProfileImage());

        return new CommonResponse(0, "Sukses", response);
    }

    @Transactional
    public CommonResponse<?> updateProfile(String token, UpdateProfileRequest request) {
        User user = jwtService.verifyToken(token);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);

        ProfileResponse response = new ProfileResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setProfileImage(user.getProfileImage());

        return new CommonResponse(0, "Update Pofile berhasil", response);
    }

    @Transactional
    public CommonResponse<?> updateImageProfile(String token, MultipartFile request) throws IOException {
        User user = jwtService.verifyToken(token);
        String ext = FilenameUtils.getExtension(request.getOriginalFilename());

        if (!ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png")) {
            return new CommonResponse<>(109, "Format Image tidak sesuai", null);
        }

        String raw = Base64.getEncoder().encodeToString(request.getBytes());

        Image image = new Image();
        image.setFilename(StringHelper.generateSandString(5) + "-" + new Date().getTime() + "." + ext);
        image.setRaw(raw);
        imageRepository.save(image);

        user.setProfileImage(image.getFilename());
        userRepository.save(user);

        ProfileResponse response = new ProfileResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setProfileImage(user.getProfileImage());

        return new CommonResponse(0, "Update Profile Image berhasil", response);
    }

    @Transactional(readOnly = true)
    public byte[] getImageProfile(String token, String filename) throws IOException {
        Optional<Image> optImage = imageRepository.getImageByFileName(filename);
        if (optImage.isPresent()) {
            return Base64.getDecoder().decode(optImage.get().getRaw());
        }
        return "Image Tidak ditemukan".getBytes();
    }
}
