/**

The Notice below must appear in each file of the Source Code of any
copy you distribute of the Licensed Product.  Contributors to any
Modifications may add their own copyright notices to identify their
own contributions.

License:

The contents of this file are subject to the CognitiveWeb Open Source
License Version 1.1 (the License).  You may not copy or use this file,
in either source code or executable form, except in compliance with
the License.  You may obtain a copy of the License from

  http://www.CognitiveWeb.org/legal/license/

Software distributed under the License is distributed on an AS IS
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.  See
the License for the specific language governing rights and limitations
under the License.

Copyrights:

Portions created by or assigned to CognitiveWeb are Copyright
(c) 2003-2003 CognitiveWeb.  All Rights Reserved.  Contact
information for CognitiveWeb is available at

  http://www.CognitiveWeb.org

Portions Copyright (c) 2002-2003 Bryan Thompson.

Acknowledgements:

Special thanks to the developers of the Jabber Open Source License 1.0
(JOSL), from which this License was derived.  This License contains
terms that differ from JOSL.

Special thanks to the CognitiveWeb Open Source Contributors for their
suggestions and support of the Cognitive Web.

Modifications:

*/
/*
 * Created on Oct 14, 2006
 */

package com.bigdata.journal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Runs all tests for all journal implementations.
 * 
 * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
 * @version $Id$
 */
public class TestAll extends TestCase {

    /**
     * 
     */
    public TestAll() {
    }

    /**
     * @param arg0
     */
    public TestAll(String arg0) {
        super(arg0);
    }

    /**
     * Returns a test that will run each of the implementation specific test
     * suites in turn.
     */
    public static Test suite()
    {

        TestSuite suite = new TestSuite("journal");

        // test the ability to (de-)serialize the root addreses.
        suite.addTestSuite( TestCommitRecordSerializer.class );
        
        // test the root block api.
        suite.addTestSuite( TestRootBlockView.class );
       
        // @todo tests of the index used map index names to indices.
        suite.addTestSuite( TestName2Addr.class );

        // tests of the index used to access historical commit records
        suite.addTestSuite( TestCommitRecordIndex.class );
        
        /*
         * Test a scalable temporary store (uses the transient and disk-only
         * buffer modes).
         */
        suite.addTestSuite( TestTemporaryStore.class );
        
        // test the different journal modes.
        
        suite.addTest( TestTransientJournal.suite() );
        
        suite.addTest( TestDirectJournal.suite() );
        
        /*
         * Note: The mapped journal is somewhat problematic and its tests are
         * disabled for the moment since (a) we have to pre-allocate large
         * extends; (b) it does not perform any better than other options; and
         * (c) we can not synchronously unmap or delete a mapped file which
         * makes cleanup of the test suites difficult and winds up spewing 200M
         * files all over your temp directory.
         */
        
//        suite.addTest( TestMappedJournal.suite() );

        suite.addTest( TestDiskJournal.suite() );

        return suite;
        
    }
    
}
