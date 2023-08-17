package TubeNova.app.controller;

import TubeNova.app.dto.LoginDto;
import TubeNova.app.dto.member.MemberCreateRequestDto;
import TubeNova.app.dto.member.MemberCreateResponseDto;
import TubeNova.app.dto.token.TokenDto;
import TubeNova.app.dto.token.TokenRequestDto;
import TubeNova.app.jwt.TokenProvider;
import TubeNova.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://tubenova.site:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthService authService;
    private final TokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<MemberCreateResponseDto> memberSignup(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        return ResponseEntity.ok(authService.memberSignup(memberCreateRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> memberLogin(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.memberLogin(loginDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @PatchMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user, @RequestBody TokenDto tokenDto) {
        System.out.println(user.getAuthorities());
        return ResponseEntity.ok(authService.logout(tokenDto.getAccessToken(), user));
    }
}
