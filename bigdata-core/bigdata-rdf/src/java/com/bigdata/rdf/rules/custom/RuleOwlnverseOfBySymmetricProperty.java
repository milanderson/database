package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlnverseOfBySymmetricProperty extends Rule {

    private static final long serialVersionUID = -7423082674586471304L;

    public RuleOwlnverseOfBySymmetricProperty(String relationName, Vocabulary vocab) {

        super("owl_invOfBySymProp",//
                new SPOPredicate(relationName, var("p"), vocab.getConstant(OWL.INVERSEOF), var("p")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDF.TYPE), vocab.getConstant(OWL.SYMMETRICPROPERTY)),
                },//
                null// constraints
        );
    }
}
