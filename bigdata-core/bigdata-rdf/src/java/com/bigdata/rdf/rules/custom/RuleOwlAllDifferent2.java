package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NE;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlAllDifferent2 extends Rule {

    private static final long serialVersionUID = -7423082674586471319L;

    public RuleOwlAllDifferent2(String relationName, Vocabulary vocab) {

        super("owl_allDiff2",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(OWL.DIFFERENTFROM), var("j")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.DISTINCTMEMBERS), var("m")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.DISTINCTMEMBERS), var("n")),
                        new SPOPredicate(relationName, var("m"), vocab.getConstant(RDF.FIRST), var("i")),
                        new SPOPredicate(relationName, var("n"), vocab.getConstant(RDF.FIRST), var("j")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NE(var("n"),var("m"))),
                        Constraint.wrap(new NE(var("j"),var("i")))
                }
        );
    }
}