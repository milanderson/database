package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlSymmetricPropertyByInverse extends Rule {

    private static final long serialVersionUID = -7423082674586471305L;

    public RuleOwlSymmetricPropertyByInverse(String relationName, Vocabulary vocab) {

        super("owl_SymPropByInverse",//
                new SPOPredicate(relationName, var("p"), vocab.getConstant(RDF.TYPE), vocab.getConstant(OWL.SYMMETRICPROPERTY)), //
                new SPOPredicate[]{ //
                    new SPOPredicate(relationName, var("p"), vocab.getConstant(OWL.INVERSEOF), var("p")),
                },//
                null// constraints
        );
    }
}
