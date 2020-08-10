package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NEConstant;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlOneOf1_2 extends Rule {

    private static final long serialVersionUID = -7423082674586471324L;

    public RuleOwlOneOf1_2(String relationName, Vocabulary vocab) {

        super("owl_oneOf1_2",//
                new SPOPredicate(relationName, var("m"), vocab.getConstant(RDFS.SUBCLASSOF), var("c")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("c"), vocab.getConstant(OWL.ONEOF), var("m")),
                        new SPOPredicate(relationName, var("m"), vocab.getConstant(RDF.REST), var("c")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NEConstant(var("n"), vocab.getConstant(RDF.NIL)))
                }
        );
    }
}
