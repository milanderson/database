package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlAttributeByHasValue extends Rule {

    private static final long serialVersionUID = -7423082674586471309L;

    public RuleOwlAttributeByHasValue(String relationName, Vocabulary vocab) {

        super("owl_AttrByHasVal",//
                new SPOPredicate(relationName, var("u"), var("p"), var("y")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.HASVALUE), var("y")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("u"), vocab.getConstant(RDF.TYPE), var("x")),
                },//
                null// constraints
        );
    }
}
