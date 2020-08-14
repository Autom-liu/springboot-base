package com.edu.scnu.common.query;

import com.edu.scnu.common.base.BaseFunction;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class QueryBuilder<T> {

    private List<SelectClause> selectClauses;

    private Creiteria<T> criteria;

    private List<OrderClause> orderClauses;

    private Class<T> entityClazz;

    private LimitClause limitClause;

    public QueryBuilder (Class<T> entityClazz) {
        this.entityClazz = entityClazz;
    }

    public Creiteria<T> createCriteria() {
        if (criteria == null) {
            this.criteria = new Creiteria<>(this);
        }
        return criteria;
    }

    public QueryBuilder<T> selectAll() {
        if (selectClauses == null) {
            selectClauses = new ArrayList<>();
        } else {
            selectClauses.clear();
        }
        List<String> fields = LambdaColumnCache.get(entityClazz);
        for (String fieldName : fields) {
            SelectClause selectClause = new SelectClause(fieldName, false);
            selectClauses.add(selectClause);
        }
        return this;
    }

    public QueryBuilder<T> select(BaseFunction<T, ?> selectField, boolean distinct) {
        if (selectClauses == null) {
            selectClauses = new ArrayList<>();
        } else {
            selectClauses.clear();
        }
        addSelectdField(selectField, distinct);
        return this;
    }

    public QueryBuilder<T> orderby(BaseFunction<T, ?> orderbyField, boolean isDesc) {
        if (orderClauses == null) {
            orderClauses = new ArrayList<>();
        } else {
            orderClauses.clear();
        }
        addOrderField(orderbyField, isDesc);
        return this;
    }

    public QueryBuilder<T> select(BaseFunction<T, ?>... selectFields) {
        if (selectClauses == null) {
            selectClauses = new ArrayList<>();
        } else {
            selectClauses.clear();
        }
        addSelectedFields(selectFields);
        return this;
    }

    public QueryBuilder<T> orderby(BaseFunction<T, ?>... orderbyFields) {
        if (orderClauses == null) {
            orderClauses = new ArrayList<>();
        } else {
            orderClauses.clear();
        }
        addOrderFields(orderbyFields);
        return this;
    }

    public QueryBuilder<T> limit(int fetchNum) {
        if (limitClause != null) {
            this.limitClause = new LimitClause(fetchNum);
        }
        return this;
    }

    public QueryBuilder<T> limit(int beginNum, int fetchNum) {
        if (limitClause != null) {
            this.limitClause = new LimitClause(beginNum, fetchNum);
        }
        return this;
    }

    public QueryBuilder<T> clearLimit() {
        this.limitClause = null;
        return this;
    }

    private void addOrderFields(BaseFunction<T, ?>[] orderbyFields) {
        for (BaseFunction<T, ?> orderField : orderbyFields) {
            addOrderField(orderField, false);
        }
    }

    private void addOrderField(BaseFunction<T, ?> orderField, boolean desc) {
        String fieldName = LambdaColumnCache.get(orderField);
        OrderClause orderClause = new OrderClause(fieldName, desc);
        orderClauses.add(orderClause);
    }

    private void addSelectedFields(BaseFunction<T, ?>[] selectFields) {
        for (BaseFunction<T, ?> selectField : selectFields) {
            addSelectdField(selectField, false);
        }
    }

    private void addSelectdField(BaseFunction<T, ?> selectField, boolean distinct) {
        String fieldName = LambdaColumnCache.get(selectField);
        SelectClause selectClause = new SelectClause(fieldName, distinct);
        selectClauses.add(selectClause);
    }

    @Getter
    public static class Creiteria<T> {

        private QueryBuilder<T> caller;

        private List<Criterion> critertions;

        public Creiteria(QueryBuilder<T> caller) {
            this.caller = caller;
            this.critertions = new ArrayList<>();
        }

        private Criterion addSimpleCriterion(String fieldName, String op, Object value) {
            Criterion criterion = new Criterion(fieldName, op, value);
            critertions.add(criterion);
            return criterion;
        }

        public Creiteria<T> andEqualTo(BaseFunction<T, ?> field, Object value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "=", value);
            }
            return this;
        }

        public Creiteria<T> andNotEqualTo(BaseFunction<T, ?> field, Object value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "!=", value);
            }
            return this;
        }

        public Creiteria<T> andGreaterThan(BaseFunction<T, ?> field, Object value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, ">", value);
            }
            return this;
        }

        public Creiteria<T> andGreaterOrEqualThan(BaseFunction<T, ?> field, Object value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, ">=", value);
            }
            return this;
        }

        public Creiteria<T> andLessThan(BaseFunction<T, ?> field, Object value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "<", value);
            }
            return this;
        }

        public Creiteria<T> andLessOrEqualThan(BaseFunction<T, ?> field, Object value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "<=", value);
            }
            return this;
        }

        public Creiteria<T> andBetween(BaseFunction<T, ?> field, Object value, Object secondValue) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "between", value);
                criterion.secondValue = secondValue;
                criterion.betweenValue = true;
            }
            return this;
        }

        public Creiteria<T> andNotBetween(BaseFunction<T, ?> field, Object value, Object secondValue) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "not between", value);
                criterion.secondValue = secondValue;
                criterion.betweenValue = true;
            }
            return this;
        }

        public Creiteria<T> andLike(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "like", value);
            }
            return this;
        }

        public Creiteria<T> andNotlike(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "not like", value);
            }
            return this;
        }

        public Creiteria<T> andLikeTrim(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                String trimVal = "%" + value + "%";
                return andLike(field, trimVal);
            }
            return this;
        }

        public Creiteria<T> andNotlikeTrim(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                String trimVal = "%" + value + "%";
                return andNotlike(field, trimVal);
            }
            return this;
        }

        public Creiteria<T> andLikePrefix(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                String prefixVal = value + "%";
                return andLike(field, prefixVal);
            }
            return this;
        }

        public Creiteria<T> andNotlikePrefix(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                String prefixVal = value + "%";
                return andNotlike(field, prefixVal);
            }
            return this;
        }

        public Creiteria<T> andLikeSuffix(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                return andLike(field, "%" + value);
            }
            return this;
        }

        public Creiteria<T> andNotlikeSuffix(BaseFunction<T, ?> field, String value) {
            if (value != null) {
                return andNotlike(field, "%" + value);
            }
            return this;
        }

        public Creiteria<T> andIn(BaseFunction<T, ?> field, Collection<Object> values) {
            if (CollectionUtils.isEmpty(values)) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "in", values);
                criterion.listValue = true;
            }
            return this;
        }

        public Creiteria<T> andNotin(BaseFunction<T, ?> field, Collection<Object> values) {
            if (CollectionUtils.isEmpty(values)) {
                String fieldName = LambdaColumnCache.get(field);
                Criterion criterion = addSimpleCriterion(fieldName, "in", values);
                criterion.listValue = true;
            }
            return this;
        }

        public Creiteria<T> andIsNull(BaseFunction<T, ?> field) {
            String fieldName = LambdaColumnCache.get(field);
            Criterion criterion = addSimpleCriterion(fieldName, "is null", null);
            criterion.novalue = true;
            return this;
        }

        public Creiteria<T> andIsNotnull(BaseFunction<T, ?> field) {
            String fieldName = LambdaColumnCache.get(field);
            Criterion criterion = addSimpleCriterion(fieldName, "is not null", null);
            criterion.novalue = true;
            return this;
        }

        public QueryBuilder<T> build() {
            if (caller.selectClauses == null) {
                caller.selectAll();
            }
            return caller;
        }

    }

    @Getter
    private static class SelectClause {

        private String fieldName;

        private boolean distinct;

        public SelectClause(String fieldName, boolean distinct) {
            this.fieldName = fieldName;
            this.distinct = distinct;
        }

    }

    @Getter
    private static class OrderClause {

        private String fieldName;

        private boolean desc;

        public OrderClause(String fieldName, boolean desc) {
            this.fieldName = fieldName;
            this.desc = desc;
        }
    }

    @Getter
    private static class LimitClause {

        private Integer beginNum;

        private Integer fetchNum;

        public LimitClause(Integer fetchNum) {
            this.beginNum = 0;
            this.fetchNum = fetchNum;
        }

        public LimitClause(Integer beginNum, Integer fetchNum) {
            this.beginNum = beginNum;
            this.fetchNum = fetchNum;
        }
    }

    @Getter
    private static class Criterion {

        private String fieldName;

        private String operation;

        private Object value;

        private Object secondValue;

        private boolean novalue;

        private boolean betweenValue;

        private boolean listValue;

        public Criterion(String fieldName, String operation, Object value) {
            this.fieldName = fieldName;
            this.operation = operation;
            this.value = value;
        }
    }

}
