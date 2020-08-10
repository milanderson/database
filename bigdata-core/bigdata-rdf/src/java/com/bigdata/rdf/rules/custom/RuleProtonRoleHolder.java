package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.ProtonT;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.RDF;

public class RuleProtonRoleHolder extends Rule {

    private static final long serialVersionUID = -7423082674586471303L;

    public RuleProtonRoleHolder(String relationName, Vocabulary vocab) {

        super("Proton_roleHolder",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(ProtonT.INVOLVEDIN), var("z")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(ProtonT.ROLEHOLDER), var("y")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(ProtonT.ROLEIN), var("z")),
                        new SPOPredicate(relationName, var("y"), vocab.getConstant(RDF.TYPE), vocab.getConstant(ProtonT.AGENT))
                },//
                null// constraints
        );
    }
}
