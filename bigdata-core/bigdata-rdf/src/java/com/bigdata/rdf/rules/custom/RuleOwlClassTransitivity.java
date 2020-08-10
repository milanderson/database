package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NE;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlClassTransitivity extends Rule {

    private static final long serialVersionUID = -7423082674586471330L;

    public RuleOwlClassTransitivity(String relationName, Vocabulary vocab) {

        super("owl_class_transitivity",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(RDF.TYPE), var("c")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(RDFS.SUBCLASSOF), var("y")),
                        new SPOPredicate(relationName, var("y"), vocab.getConstant(RDF.TYPE), var("c")),
                },//
                new IConstraint[] {
                    Constraint.wrap(new NE(var("x"), var("y")) )
                }// constraints
        );
    }
}