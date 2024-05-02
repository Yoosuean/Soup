package io.ssafy.soupapi.global.security.api;

import io.ssafy.soupapi.global.common.code.SuccessCode;
import io.ssafy.soupapi.global.common.response.BaseResponse;
import io.ssafy.soupapi.global.security.dto.RefreshTokenDto;
import io.ssafy.soupapi.global.security.dto.TokenDto;
import io.ssafy.soupapi.global.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증", description = "JWT 토큰 관리")
public class AuthController {

    private final JwtService jwtService;

    @Operation(summary = "accessToken 재발급 요청", description =
        "accessToken이 만료되면 **\"code\": \"EXPIRED\"**라는 응답을 받는다.\n\n" +
        "responseBody에 refreshToken을 보냄으로써 accessToken과 refreshToken을 재발급 받는다.\n\n" +
        "이때, **Authorization에 accessToken을 보낼 필요는 없다.** (swagger에는 아직 반영 안 됨 - 수현)\n\n" +
        "(참고) accessToken은 5분, refreshToken은 14일간 유효하다.\n\n"
    )
    @PostMapping("/token/refresh")
    public ResponseEntity<BaseResponse<TokenDto>> regenerateJwtToken (
        @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        TokenDto tokenDto = jwtService.regenerateJwtTokens(refreshTokenDto.refreshToken());
        return BaseResponse.success(
            SuccessCode.SELECT_SUCCESS,
            tokenDto
        );
    }

}
