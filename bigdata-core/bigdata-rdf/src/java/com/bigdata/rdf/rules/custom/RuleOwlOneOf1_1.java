package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NEConstant;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlOneOf1_1 extends Rule {

    private static final long serialVersionUID = -7423082674586471323L;

    public RuleOwlOneOf1_1(String relationName, Vocabulary vocab) {

        super("owl_oneOf1_1",//
                new SPOPredicate(relationName, var("m"), vocab.getConstant(OWL.ONEOF), var("n")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("c"), vocab.getConstant(OWL.ONEOF), var("m")),
                        new SPOPredicate(relationName, var("m"), vocab.getConstant(RDF.REST), var("n")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NEConstant(var("n"), vocab.getConstant(RDF.NIL)))
                }
        );
    }
}
