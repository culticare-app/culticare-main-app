package com.culticare.member.controller;

import com.culticare.jwt.service.JwtService;
import com.culticare.jwt.service.dto.CustomUserDetails;
import com.culticare.member.controller.dto.request.MemberSaveRequestDto;
import com.culticare.member.controller.dto.response.MemberSaveResponseDto;
import com.culticare.member.controller.dto.response.TokenResponseDto;
import com.culticare.member.dto.request.MemberLoginRequestDto;
import com.culticare.member.dto.response.MemberLoginResponseDto;
import com.culticare.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<MemberSaveResponseDto> join(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {

        MemberSaveResponseDto memberSaveResponseDto = memberService.saveMember(memberSaveRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }

    // 아이디 중복 체크
    @GetMapping("/check-id")
    public ResponseEntity<Void> checkLoginId(@RequestParam("loginId") String loginId) {

        memberService.checkDuplicateMemberLoginId(loginId);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {

        MemberLoginResponseDto memberLoginResponseDto = memberService.login(memberLoginRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberLoginResponseDto);
    }

    // 토근 재발급
    @PostMapping("/token")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody Map<String, String> refreshToken) {

        // Refresh Token 검증
        String recreatedAccessToken = jwtService.validateRefreshToken(refreshToken.get("refreshToken"));

        // Access Token 재발급
        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(recreatedAccessToken)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {

        memberService.logout(accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 회원가입
    @GetMapping("/auth/member-info")
    public ResponseEntity<MemberSaveResponseDto> getMember(@AuthenticationPrincipal CustomUserDetails userDetails) {

        MemberSaveResponseDto memberSaveResponseDto = memberService.getMember(userDetails.getMember());

        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }


}
