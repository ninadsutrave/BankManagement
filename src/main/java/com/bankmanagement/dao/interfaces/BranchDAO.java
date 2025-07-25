package com.bankmanagement.dao.interfaces;

import com.bankmanagement.entity.branch.BranchDTO;

import java.util.List;
import java.util.Optional;

public interface BranchDAO {
  Optional<List<BranchDTO>> getAllBranches();
}
