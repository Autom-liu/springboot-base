package com.edu.scnu.baseinfo.bean;

import java.util.ArrayList;
import java.util.List;

import com.edu.scnu.common.base.BaseExample;

public class AccountExample implements BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAccIdIsNull() {
            addCriterion("`acc_id` is null");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNotNull() {
            addCriterion("`acc_id` is not null");
            return (Criteria) this;
        }

        public Criteria andAccIdEqualTo(Integer value) {
            addCriterion("`acc_id` =", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotEqualTo(Integer value) {
            addCriterion("`acc_id` <>", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThan(Integer value) {
            addCriterion("`acc_id` >", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`acc_id` >=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThan(Integer value) {
            addCriterion("`acc_id` <", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThanOrEqualTo(Integer value) {
            addCriterion("`acc_id` <=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdIn(List<Integer> values) {
            addCriterion("`acc_id` in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotIn(List<Integer> values) {
            addCriterion("`acc_id` not in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdBetween(Integer value1, Integer value2) {
            addCriterion("`acc_id` between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`acc_id` not between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeIsNull() {
            addCriterion("`acc_sys_type` is null");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeIsNotNull() {
            addCriterion("`acc_sys_type` is not null");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeEqualTo(String value) {
            addCriterion("`acc_sys_type` =", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeNotEqualTo(String value) {
            addCriterion("`acc_sys_type` <>", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeGreaterThan(String value) {
            addCriterion("`acc_sys_type` >", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`acc_sys_type` >=", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeLessThan(String value) {
            addCriterion("`acc_sys_type` <", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeLessThanOrEqualTo(String value) {
            addCriterion("`acc_sys_type` <=", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeLike(String value) {
            addCriterion("`acc_sys_type` like", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeNotLike(String value) {
            addCriterion("`acc_sys_type` not like", value, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeIn(List<String> values) {
            addCriterion("`acc_sys_type` in", values, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeNotIn(List<String> values) {
            addCriterion("`acc_sys_type` not in", values, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeBetween(String value1, String value2) {
            addCriterion("`acc_sys_type` between", value1, value2, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccSysTypeNotBetween(String value1, String value2) {
            addCriterion("`acc_sys_type` not between", value1, value2, "accSysType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeIsNull() {
            addCriterion("`acc_user_type` is null");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeIsNotNull() {
            addCriterion("`acc_user_type` is not null");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeEqualTo(String value) {
            addCriterion("`acc_user_type` =", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeNotEqualTo(String value) {
            addCriterion("`acc_user_type` <>", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeGreaterThan(String value) {
            addCriterion("`acc_user_type` >", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`acc_user_type` >=", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeLessThan(String value) {
            addCriterion("`acc_user_type` <", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeLessThanOrEqualTo(String value) {
            addCriterion("`acc_user_type` <=", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeLike(String value) {
            addCriterion("`acc_user_type` like", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeNotLike(String value) {
            addCriterion("`acc_user_type` not like", value, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeIn(List<String> values) {
            addCriterion("`acc_user_type` in", values, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeNotIn(List<String> values) {
            addCriterion("`acc_user_type` not in", values, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeBetween(String value1, String value2) {
            addCriterion("`acc_user_type` between", value1, value2, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccUserTypeNotBetween(String value1, String value2) {
            addCriterion("`acc_user_type` not between", value1, value2, "accUserType");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNull() {
            addCriterion("`acc_name` is null");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNotNull() {
            addCriterion("`acc_name` is not null");
            return (Criteria) this;
        }

        public Criteria andAccNameEqualTo(String value) {
            addCriterion("`acc_name` =", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotEqualTo(String value) {
            addCriterion("`acc_name` <>", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThan(String value) {
            addCriterion("`acc_name` >", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThanOrEqualTo(String value) {
            addCriterion("`acc_name` >=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThan(String value) {
            addCriterion("`acc_name` <", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThanOrEqualTo(String value) {
            addCriterion("`acc_name` <=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLike(String value) {
            addCriterion("`acc_name` like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotLike(String value) {
            addCriterion("`acc_name` not like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameIn(List<String> values) {
            addCriterion("`acc_name` in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotIn(List<String> values) {
            addCriterion("`acc_name` not in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameBetween(String value1, String value2) {
            addCriterion("`acc_name` between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotBetween(String value1, String value2) {
            addCriterion("`acc_name` not between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccIconIsNull() {
            addCriterion("`acc_icon` is null");
            return (Criteria) this;
        }

        public Criteria andAccIconIsNotNull() {
            addCriterion("`acc_icon` is not null");
            return (Criteria) this;
        }

        public Criteria andAccIconEqualTo(String value) {
            addCriterion("`acc_icon` =", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconNotEqualTo(String value) {
            addCriterion("`acc_icon` <>", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconGreaterThan(String value) {
            addCriterion("`acc_icon` >", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconGreaterThanOrEqualTo(String value) {
            addCriterion("`acc_icon` >=", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconLessThan(String value) {
            addCriterion("`acc_icon` <", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconLessThanOrEqualTo(String value) {
            addCriterion("`acc_icon` <=", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconLike(String value) {
            addCriterion("`acc_icon` like", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconNotLike(String value) {
            addCriterion("`acc_icon` not like", value, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconIn(List<String> values) {
            addCriterion("`acc_icon` in", values, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconNotIn(List<String> values) {
            addCriterion("`acc_icon` not in", values, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconBetween(String value1, String value2) {
            addCriterion("`acc_icon` between", value1, value2, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccIconNotBetween(String value1, String value2) {
            addCriterion("`acc_icon` not between", value1, value2, "accIcon");
            return (Criteria) this;
        }

        public Criteria andAccRemarkIsNull() {
            addCriterion("`acc_remark` is null");
            return (Criteria) this;
        }

        public Criteria andAccRemarkIsNotNull() {
            addCriterion("`acc_remark` is not null");
            return (Criteria) this;
        }

        public Criteria andAccRemarkEqualTo(String value) {
            addCriterion("`acc_remark` =", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkNotEqualTo(String value) {
            addCriterion("`acc_remark` <>", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkGreaterThan(String value) {
            addCriterion("`acc_remark` >", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("`acc_remark` >=", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkLessThan(String value) {
            addCriterion("`acc_remark` <", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkLessThanOrEqualTo(String value) {
            addCriterion("`acc_remark` <=", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkLike(String value) {
            addCriterion("`acc_remark` like", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkNotLike(String value) {
            addCriterion("`acc_remark` not like", value, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkIn(List<String> values) {
            addCriterion("`acc_remark` in", values, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkNotIn(List<String> values) {
            addCriterion("`acc_remark` not in", values, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkBetween(String value1, String value2) {
            addCriterion("`acc_remark` between", value1, value2, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccRemarkNotBetween(String value1, String value2) {
            addCriterion("`acc_remark` not between", value1, value2, "accRemark");
            return (Criteria) this;
        }

        public Criteria andAccAmountIsNull() {
            addCriterion("`acc_amount` is null");
            return (Criteria) this;
        }

        public Criteria andAccAmountIsNotNull() {
            addCriterion("`acc_amount` is not null");
            return (Criteria) this;
        }

        public Criteria andAccAmountEqualTo(Long value) {
            addCriterion("`acc_amount` =", value, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountNotEqualTo(Long value) {
            addCriterion("`acc_amount` <>", value, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountGreaterThan(Long value) {
            addCriterion("`acc_amount` >", value, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("`acc_amount` >=", value, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountLessThan(Long value) {
            addCriterion("`acc_amount` <", value, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountLessThanOrEqualTo(Long value) {
            addCriterion("`acc_amount` <=", value, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountIn(List<Long> values) {
            addCriterion("`acc_amount` in", values, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountNotIn(List<Long> values) {
            addCriterion("`acc_amount` not in", values, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountBetween(Long value1, Long value2) {
            addCriterion("`acc_amount` between", value1, value2, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccAmountNotBetween(Long value1, Long value2) {
            addCriterion("`acc_amount` not between", value1, value2, "accAmount");
            return (Criteria) this;
        }

        public Criteria andAccHasChildIsNull() {
            addCriterion("`acc_has_child` is null");
            return (Criteria) this;
        }

        public Criteria andAccHasChildIsNotNull() {
            addCriterion("`acc_has_child` is not null");
            return (Criteria) this;
        }

        public Criteria andAccHasChildEqualTo(Boolean value) {
            addCriterion("`acc_has_child` =", value, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildNotEqualTo(Boolean value) {
            addCriterion("`acc_has_child` <>", value, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildGreaterThan(Boolean value) {
            addCriterion("`acc_has_child` >", value, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`acc_has_child` >=", value, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildLessThan(Boolean value) {
            addCriterion("`acc_has_child` <", value, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildLessThanOrEqualTo(Boolean value) {
            addCriterion("`acc_has_child` <=", value, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildIn(List<Boolean> values) {
            addCriterion("`acc_has_child` in", values, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildNotIn(List<Boolean> values) {
            addCriterion("`acc_has_child` not in", values, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildBetween(Boolean value1, Boolean value2) {
            addCriterion("`acc_has_child` between", value1, value2, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccHasChildNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`acc_has_child` not between", value1, value2, "accHasChild");
            return (Criteria) this;
        }

        public Criteria andAccCanPayIsNull() {
            addCriterion("`acc_can_pay` is null");
            return (Criteria) this;
        }

        public Criteria andAccCanPayIsNotNull() {
            addCriterion("`acc_can_pay` is not null");
            return (Criteria) this;
        }

        public Criteria andAccCanPayEqualTo(Boolean value) {
            addCriterion("`acc_can_pay` =", value, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayNotEqualTo(Boolean value) {
            addCriterion("`acc_can_pay` <>", value, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayGreaterThan(Boolean value) {
            addCriterion("`acc_can_pay` >", value, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`acc_can_pay` >=", value, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayLessThan(Boolean value) {
            addCriterion("`acc_can_pay` <", value, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayLessThanOrEqualTo(Boolean value) {
            addCriterion("`acc_can_pay` <=", value, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayIn(List<Boolean> values) {
            addCriterion("`acc_can_pay` in", values, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayNotIn(List<Boolean> values) {
            addCriterion("`acc_can_pay` not in", values, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayBetween(Boolean value1, Boolean value2) {
            addCriterion("`acc_can_pay` between", value1, value2, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanPayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`acc_can_pay` not between", value1, value2, "accCanPay");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferIsNull() {
            addCriterion("`acc_can_transfer` is null");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferIsNotNull() {
            addCriterion("`acc_can_transfer` is not null");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferEqualTo(Boolean value) {
            addCriterion("`acc_can_transfer` =", value, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferNotEqualTo(Boolean value) {
            addCriterion("`acc_can_transfer` <>", value, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferGreaterThan(Boolean value) {
            addCriterion("`acc_can_transfer` >", value, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`acc_can_transfer` >=", value, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferLessThan(Boolean value) {
            addCriterion("`acc_can_transfer` <", value, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferLessThanOrEqualTo(Boolean value) {
            addCriterion("`acc_can_transfer` <=", value, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferIn(List<Boolean> values) {
            addCriterion("`acc_can_transfer` in", values, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferNotIn(List<Boolean> values) {
            addCriterion("`acc_can_transfer` not in", values, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferBetween(Boolean value1, Boolean value2) {
            addCriterion("`acc_can_transfer` between", value1, value2, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccCanTransferNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`acc_can_transfer` not between", value1, value2, "accCanTransfer");
            return (Criteria) this;
        }

        public Criteria andAccParentIdIsNull() {
            addCriterion("`acc_parent_id` is null");
            return (Criteria) this;
        }

        public Criteria andAccParentIdIsNotNull() {
            addCriterion("`acc_parent_id` is not null");
            return (Criteria) this;
        }

        public Criteria andAccParentIdEqualTo(Integer value) {
            addCriterion("`acc_parent_id` =", value, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdNotEqualTo(Integer value) {
            addCriterion("`acc_parent_id` <>", value, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdGreaterThan(Integer value) {
            addCriterion("`acc_parent_id` >", value, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`acc_parent_id` >=", value, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdLessThan(Integer value) {
            addCriterion("`acc_parent_id` <", value, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("`acc_parent_id` <=", value, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdIn(List<Integer> values) {
            addCriterion("`acc_parent_id` in", values, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdNotIn(List<Integer> values) {
            addCriterion("`acc_parent_id` not in", values, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdBetween(Integer value1, Integer value2) {
            addCriterion("`acc_parent_id` between", value1, value2, "accParentId");
            return (Criteria) this;
        }

        public Criteria andAccParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`acc_parent_id` not between", value1, value2, "accParentId");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("`is_del` is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("`is_del` is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Boolean value) {
            addCriterion("`is_del` =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("`is_del` <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("`is_del` >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_del` >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("`is_del` <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_del` <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Boolean> values) {
            addCriterion("`is_del` in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Boolean> values) {
            addCriterion("`is_del` not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_del` between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_del` not between", value1, value2, "isDel");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
