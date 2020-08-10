package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NE;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlEquivalentPropertyBySubProperty extends Rule {

    private static final long serialVersionUID = -7423082674586471306L;

    public RuleOwlEquivalentPropertyBySubProperty(String relationName, Vocabulary vocab) {

        super("owl_EquivPropBySubProp",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.EQUIVALENTPROPERTY), var("y")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(RDFS.SUBPROPERTYOF), var("y")),
                        new SPOPredicate(relationName, var("y"), vocab.getConstant(RDFS.SUBPROPERTYOF), var("x")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NE(var("x"),var("y")))
                }// constraints
        );
    }
}