package com.bankmanagement.entity.account;

/*
  ACTIVE – Account is fully operational.
  SUSPENDED – Temporarily disabled due to suspicious activity or user request.
  CLOSED – Permanently closed account; no transactions allowed.
  PENDING_APPROVAL – Account created but awaiting admin verification.
  DORMANT – Inactive for a long period (typically 12+ months) requires reactivation.
 */

public enum AccountStatus {
  PENDING_APPROVAL,
  ACTIVE,
  DORMANT,
  SUSPENDED,
  CLOSED
}
