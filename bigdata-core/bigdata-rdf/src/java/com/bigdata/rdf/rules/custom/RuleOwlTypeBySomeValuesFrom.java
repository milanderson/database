package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlTypeBySomeValuesFrom extends Rule {

    private static final long serialVersionUID = -7423082674586471310L;

    public RuleOwlTypeBySomeValuesFrom(String relationName, Vocabulary vocab) {

        super("owl_typeBySomeVal",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("r")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("q"), vocab.getConstant(RDF.TYPE), var("c")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.SOMEVALUESFROM), var("c")),
                        new SPOPredicate(relationName, var("i"), var("p"), var("q")),
                },//
                null// constraints
        );
    }
}
