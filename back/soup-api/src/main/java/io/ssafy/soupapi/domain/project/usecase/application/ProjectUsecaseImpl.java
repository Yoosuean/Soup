package io.ssafy.soupapi.domain.project.usecase.application;

import io.ssafy.soupapi.domain.member.dao.MemberRepository;
import io.ssafy.soupapi.domain.member.entity.Member;
import io.ssafy.soupapi.domain.project.mongodb.application.MProjectService;
import io.ssafy.soupapi.domain.project.mongodb.dto.request.UpdateProjectInfo;
import io.ssafy.soupapi.domain.project.mongodb.dto.request.UpdateProjectJiraKey;
import io.ssafy.soupapi.domain.project.mongodb.dto.request.UpdateProjectProposal;
import io.ssafy.soupapi.domain.project.mongodb.dto.response.GetProjectInfo;
import io.ssafy.soupapi.domain.project.mongodb.dto.response.GetProjectJiraKey;
import io.ssafy.soupapi.domain.project.mongodb.dto.response.GetProjectProposal;
import io.ssafy.soupapi.domain.project.postgresql.application.PProjectService;
import io.ssafy.soupapi.domain.project.postgresql.entity.ProjectRole;
import io.ssafy.soupapi.domain.project.usecase.dto.request.CreateProjectDto;
import io.ssafy.soupapi.domain.project.usecase.dto.request.InviteTeammate;
import io.ssafy.soupapi.domain.projectauth.dao.ProjectAuthRepository;
import io.ssafy.soupapi.global.common.code.ErrorCode;
import io.ssafy.soupapi.global.exception.BaseExceptionHandler;
import io.ssafy.soupapi.global.security.TemporalMember;
import io.ssafy.soupapi.global.util.GmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProjectUsecaseImpl implements ProjectUsecase {
    private final MProjectService mProjectService;
    private final PProjectService pProjectService;
    private final MemberRepository memberRepository;
    private final ProjectAuthRepository projectAuthRepository;
    private final GmailUtil gmailUtil;

    /**
     * 프로젝트 생성
     * 1. MongoDB 프로젝트 생성
     * 2. PostgreSQL 프로젝트 등록 및 접속 권한 부여
     *
     * @param projectName projectName to make project
     * @param member      project maker
     * @return mongodb project objectId
     */
    @Transactional
    @Override
    public String createProject(CreateProjectDto createProjectDto, TemporalMember temporalMember) {
        var projectId = mProjectService.createProject(createProjectDto, temporalMember); // TODO: member security 적용
        pProjectService.registProject(projectId.toHexString(), createProjectDto, temporalMember); // TODO: member security 적용
        return projectId.toHexString();
    }
}
