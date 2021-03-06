package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlSubClassBetweenAllValues extends Rule {

    private static final long serialVersionUID = -7423082674586471327L;

    public RuleOwlSubClassBetweenAllValues(String relationName, Vocabulary vocab) {

        super("owl_subClassBetweenAllVal",//
                new SPOPredicate(relationName, var("s"), vocab.getConstant(RDFS.SUBCLASSOF), var("r")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ALLVALUESFROM), var("c")),
                        new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.ONPROPERTY), var("q")),
                        new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.ALLVALUESFROM), var("d")),
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDFS.SUBPROPERTYOF), var("q")),
                        new SPOPredicate(relationName, var("d"), vocab.getConstant(RDFS.SUBCLASSOF), var("c")),
                },//
                null
        );
    }
}