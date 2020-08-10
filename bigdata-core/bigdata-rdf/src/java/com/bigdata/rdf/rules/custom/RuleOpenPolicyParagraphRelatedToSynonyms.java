package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.OpenPolicy;
import com.bigdata.rdf.vocab.custom.ProtonKM;
import com.bigdata.rdf.vocab.custom.SKOS;
import com.bigdata.relation.rule.Rule;

public class RuleOpenPolicyParagraphRelatedToSynonyms extends Rule {

    private static final long serialVersionUID = -7423082674586471333L;

    public RuleOpenPolicyParagraphRelatedToSynonyms(String relationName, Vocabulary vocab) {

        super("paragraphRelatedToSynonyms",//
                new SPOPredicate(relationName, var("para"), vocab.getConstant(OpenPolicy.INDIRECTLYRELATEDTO), var("synonym")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("para"), vocab.getConstant(ProtonKM.MENTIONS), var("term")),
                        new SPOPredicate(relationName, var("term"), vocab.getConstant(SKOS.SYNONYM), var("synonym")),
                },//
                null
        );
    }
}