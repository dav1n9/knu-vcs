package com.example.knu_vcs.controller;

import com.example.knu_vcs.domain.Version;
import com.example.knu_vcs.dto.AddVersionRequestDto;
import com.example.knu_vcs.dto.UpdateVersionRequestDto;
import com.example.knu_vcs.dto.VersionResponseDto;
import com.example.knu_vcs.service.VersionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class VersionController {

    private final VersionService versionService;

    @PostMapping("/vercontrol/save") // api는 복수형으로 사용.
    public ResponseEntity<Version> addArticle(@RequestBody AddVersionRequestDto addVersionRequestDto) {
        // @RequestBody : servlet에 담겨오는 객체임을 명시한다.

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(versionService.save(addVersionRequestDto));
    }

    @GetMapping("/vercontrol/getConfigAll")
    public ResponseEntity<List<Version>> findAllArticles() {

        List<Version> versions = versionService.findAll();
//                .stream()
//                .map(VersionResponseDto::new)
//                .collect(Collectors.toList());

        return ResponseEntity.ok().body(versions);
    }

    @GetMapping("/vercontrol/getConfig/{id}")
    public ResponseEntity<VersionResponseDto> findVersion(@PathVariable("id") Long id) {
        System.out.println(id);
        System.out.println(versionService.findById(id).getVer());
        System.out.println(new VersionResponseDto(versionService.findById(id)).getVer());
        return ResponseEntity.ok()
                .body(new VersionResponseDto(versionService.findById(id)));
    }


    // update 기능
    @PutMapping("/vercontrol/getConfig/{id}")
    public ResponseEntity<Version> updateArticle(@PathVariable("id") Long id,
                                                 @RequestBody UpdateVersionRequestDto requestDto) {
        Version updatedVersion = versionService.update(id, requestDto);
        return ResponseEntity.ok().body(updatedVersion);
    }

}
