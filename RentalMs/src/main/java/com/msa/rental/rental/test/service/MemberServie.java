package com.msa.rental.rental.test.service;

import com.msa.rental.rental.test.Member;
import com.msa.rental.rental.test.Study;
import java.util.Optional;

public interface MemberServie {

  Optional<String> findById(Long id);

  void notify(Study study);

  void alarm(Member member);
}
