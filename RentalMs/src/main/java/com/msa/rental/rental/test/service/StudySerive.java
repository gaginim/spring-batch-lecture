package com.msa.rental.rental.test.service;

import com.msa.rental.rental.test.Member;
import com.msa.rental.rental.test.Study;
import com.msa.rental.rental.test.repository.StudyRepository;
import java.util.Optional;
import org.springframework.util.Assert;

public class StudySerive {
  private final MemberServie memberServie;
  private final StudyRepository repository;

  public StudySerive(MemberServie memberServie, StudyRepository repository) {

    Assert.notNull(memberServie, "required to memberService");
    Assert.notNull(repository, "required to repository");

    this.memberServie = memberServie;
    this.repository = repository;
  }

  public Study ofCreateStudy(Long memberId, Study study) {
    Optional<String> name = memberServie.findById(memberId);
    if (name.isEmpty()) throw new IllegalArgumentException("there is no member name");

    study.setName(name.get());
    Study result = repository.save(study);

    memberServie.notify(result);
    memberServie.alarm(new Member(memberId, study.getName()));
    return result;
  }
}
