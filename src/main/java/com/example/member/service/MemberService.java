package com.example.member.service;

import com.example.member.dto.MemberDto;
import com.example.member.entity.MemberEntity;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto) {
        //1.dto -> entity 변환
        //2. repository의 save 메소드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);

    }

    public MemberDto login(MemberDto memberDto) {
        /* 1. 회원이 입력한 이메일로 db에서 조회
           2. db에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는 판단*/
        Optional<MemberEntity> byMemberemail = memberRepository.findByMemberemail(memberDto.getMemberEmail());
        if (byMemberemail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다
            MemberEntity memberEntity = byMemberemail.get();
            if (memberEntity.getMemberpassword().equals(memberDto.getMemberPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDTO(memberEntity);
                return dto;

            }else { // 비밀번호 불일치(로그인 실패
                return null;
            }

        } else {
            //조회 결과가 없다.(해당 이메일을 가진 회원이 없다)
            return null;
        }

    }

    public List<MemberDto> findAll() {
        List<MemberEntity> all = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (MemberEntity memberEntity : all) {
            MemberDto dto = MemberDto.toMemberDTO(memberEntity);
            memberDtoList.add(dto);
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Optional<MemberEntity> byId = memberRepository.findById(id);
        if (byId.isPresent()) {
            return MemberDto.toMemberDTO(byId.get());
        }else {
            return null;
        }
    }

    public MemberDto updateForm(String myEmail) {
        Optional<MemberEntity> byMemberemail = memberRepository.findByMemberemail(myEmail);
        if (byMemberemail.isPresent()) {
            return MemberDto.toMemberDTO(byMemberemail.get());
        }else {
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        //id가있으면 update문, id가없으면 insert문
        memberRepository.save(MemberEntity.toMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
