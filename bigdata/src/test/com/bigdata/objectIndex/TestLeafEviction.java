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
 * Created on Nov 18, 2006
 */

package com.bigdata.objectIndex;

import java.util.Stack;

import junit.framework.AssertionFailedError;

import com.bigdata.cache.HardReferenceCache;
import com.bigdata.journal.IRawStore;

/**
 * Test suite for the incremental eviction of leaves onto the store.
 * 
 * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
 * @version $Id$
 */
public class TestLeafEviction extends AbstractBTreeTestCase {

    /**
     * 
     */
    public TestLeafEviction() {
    }

    /**
     * @param name
     */
    public TestLeafEviction(String name) {
        super(name);
    }

    public BTree getEvictionBTree(int branchingFactor,int cacheSize,HardReferenceCache<PO> hardReferenceQueue) {
        
        IRawStore store = new SimpleStore();

        BTree btree = new BTree(store, branchingFactor, hardReferenceQueue);

        return btree;
        
    }

    /**
     * <p>
     * Test of leaf eviction using a tree with a branching factor of four (4)
     * and a hard reference queue capacity of two (2). A sequence of keys is
     * inserted into the tree. Evictions from the hard reference queue are
     * explicitly monitor. Based on knowledge of when leaves will split for that
     * key sequence and a known split rule, evictions are either allowed for a
     * specific reference or disallowed. The test validates that the expected
     * references are evicted at the expected times and that the post-conditions
     * of eviction hold true for the leaf in the tree and for its parent.
     * </p>
     * <p>
     * After validating leaf eviction, the test also performs a commit, so that
     * all nodes will be made immutable, and then inserts another key, which
     * triggers copy-on-write, and validates the post-conditions.
     * </p>
     */
    public void test_leafEviction01() {
        
        // listener initially disallows any evictions.
        final MyLeafEvictionListener listener = new MyLeafEvictionListener();

        /*
         * The hard reference queue has a capacity of two (2) leaves and does
         * NOT scan for a matching reference before appending another reference
         * to the queue. This gives the queue a deterministic behavior that is
         * easy for us to test.
         */
        final MyHardReferenceCache<PO> hardReferenceQueue = new MyHardReferenceCache<PO>(listener,2,0);
        assertEquals(2,hardReferenceQueue.capacity());
        assertEquals(0,hardReferenceQueue.nscan());
        assertEquals(listener,hardReferenceQueue.getListener());
        
        // The btree.
        final BTree btree = getEvictionBTree(4,2,hardReferenceQueue);
        
        // The hard reference queue.
        assertEquals(hardReferenceQueue,btree.hardReferenceQueue);
        
        // The initial root.
        final Leaf a = (Leaf) btree.getRoot();
        
        /*
         * The expected state of the hard reference queue - it should only
         * contain the root leaf of the tree.
         */
        assertEquals(new PO[]{a},hardReferenceQueue.toArray());
        
        // The known set of keys.
        int keys[] = new int[]{1,3,5,7,9,10,11,2,4};

        // Entries to be inserted with those keys (allows validation).
        Entry entries[] = new Entry[keys.length];
        
        for( int i=0; i<keys.length; i++) {
            
            entries[i] = new Entry();
            
        }
        
        int n = 0;

        /*
         * Insert keys, filling up the root leaf.
         */
        btree.insert(keys[n], entries[n]); n++;
        btree.insert(keys[n], entries[n]); n++;
        btree.insert(keys[n], entries[n]); n++;
        btree.insert(keys[n], entries[n]); n++;
        // verify the keys in the root leaf before the split.
        assertEquals(new int[]{1,3,5,7},a.keys);
        
        /*
         * Split the root leaf (a). This creates another leaf (b) and a new root
         * node (c).
         */
        assertEquals("height",0,btree.height);
        btree.insert(keys[n], entries[n]); n++;
        assertEquals("height",1,btree.height);
        assertNotSame("root",a,btree.root);
        final Node c = (Node) btree.root;
        assertEquals(new int[]{5,0,0},c.keys);
        assertEquals( a, c.getChild(0));
        assertEquals(new int[]{1,3,0,0},a.keys);
        final Leaf b = (Leaf) c.getChild(1);
        assertEquals(new int[]{5,7,9,0},b.keys);

        // The hard reference queue should now contain both leaves.
        assertEquals(new PO[]{a,b},hardReferenceQueue.toArray());

        // insert another key.
        btree.insert(keys[n], entries[n]); n++;
        
        /*
         * Split (b).
         */
        // verify the state of (b) before the split.
        assertEquals(new int[]{5,7,9,10},b.keys);
        // the split should evict (a).
        listener.setExpectedRef(a);
        // verify that (a) is dirty and not persistent.
        assertTrue(a.isDirty());
        assertFalse(a.isPersistent());
        // verify that (c) has (a) on its dirty list.
        assertTrue(c.dirtyChildren.contains(a));
        // verify that (a) has (c) as its parent.
        assertEquals(c,a.parent.get());
        // insert the key, will split (b) and evict (a).
        btree.insert(keys[n], entries[n]); n++;
        // verify that (a) was evicted.
        listener.assertEvicted();
        // verify that (a) is now clean and persistent.
        assertFalse(a.isDirty());
        assertTrue(a.isPersistent());
        // verify that (c) has the key for (a) at index 0.
        assertEquals(a,c.childRefs[0].get());
        assertEquals(a.getIdentity(),c.childKeys[0]);
        // verify that (c) no longer has (a) on its dirty list.
        assertFalse(c.dirtyChildren.contains(a));
        // verify that (a) has (c) as its parent.
        assertEquals(c,a.parent.get());
        // verify keys on (b) post-split.
        assertEquals(new int[]{5,7,0,0},b.keys);
        // get the new leaf (d).
        final Leaf d = (Leaf)c.getChild(2);
        // verify keys on the new leaf.
        assertEquals(new int[]{9,10,11,0},d.keys);
        // verify new queue state.
        assertEquals(new PO[]{b,d},hardReferenceQueue.toArray());

        /*
         * Insert a key (2) into (a). Since (a) was just evicted, this forces
         * copy on write of (a) yielding (a1). This also causes a cache eviction
         * for (b) since a new leaf (a1) is added to the hard reference queue.
         */
        // verify that (a) is still accessible from (c).
        assertEquals(a,c.getChild(0));
        // verify childKey at that index is (a).
        assertEquals(a.getIdentity(),c.childKeys[0]);
        // verify that (a) is persistent.
        assertTrue(a.isPersistent());
        // verify that (a) is not dirty.
        assertFalse(a.isDirty());
        // verify keys for a (@todo also vet values).
        assertEquals(new int[]{1,3,0,0},a.keys);
        // verify the queue state.
        assertEquals(new PO[]{b,d},hardReferenceQueue.toArray());
        // set the expected eviction.
        listener.setExpectedRef(b);
        // insert the key, forces copy-on-write of (a) and eviction of (b).
        btree.insert(keys[n], entries[n]); n++;
        // verify (b) was evicted.
        listener.assertEvicted();
        // verify childKey at that index was cleared since a1 is NOT persistent.
        assertEquals(PO.NULL,c.childKeys[0]);
        // verify child at index is no longer (a).
        assertNotSame(a,c.getChild(0));
        // get the new version of (a) from (c).
        final Leaf a1 = (Leaf) c.getChild(0);
        // verify (a) != (a1).
        assertNotSame(a,a1);
        // verify that (a) still is persistent.
        assertTrue(a.isPersistent());
        // verify that (a) still is not dirty.
        assertFalse(a.isDirty());
        // verify that (a1) is NOT persistent.
        assertFalse(a1.isPersistent());
        // verify that (a1) is dirty.
        assertTrue(a1.isDirty());
        // verify keys on (a) were NOT changed.
        assertEquals(new int[]{1,3,0,0},a.keys);
        // verify expected keys on (a1).
        assertEquals(new int[]{1,2,3,0},a1.keys);
        // verify the new queue state.
        assertEquals(new PO[]{d,a1},hardReferenceQueue.toArray());
        
        /*
         * @todo Should a.parent be cleared?
         * 
         * @todo Verify a was removed from the node set on the btree.
         */
        
        
    }

    /**
     * Extend to explicitly control when an eviction notice is expected and to
     * verify that eviction notices are received as expected.
     * 
     * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
     * @version $Id$
     */
    public static class MyLeafEvictionListener extends
            DefaultLeafEvictionListener {

        /**
         * Set the next N expected references for eviction notices.  You can
         * only do this when nothing is currently expected.
         * 
         * @param refs
         *            The expected references.
         * 
         * @exception IllegalStateExecption
         *                unless there is no current expected reference.
         */
        public void setExpectedRefs(PO[] refs) {

            if (expectedRef != null) {

                throw new IllegalStateException();

            }

            assert refs != null;

            assert refs.length > 0;

            for (int i = refs.length - 1; i >= 0; i--) {

                PO ref = refs[i];

                assert ref != null;

                expectedRefs.push(ref);

            }

            setExpectedRef(expectedRefs.pop());

        }

        Stack<PO> expectedRefs = new Stack<PO>();

        /**
         * Set the expected reference for the next eviction notice. The listener
         * will thrown an exception if there is a cache eviction unless you
         * first invoke this method.
         * 
         * @param ref
         *            The expected reference or null to cause the listener to
         *            throw an exception if a reference is evicted.
         */
        public void setExpectedRef(PO ref) {

            this.expectedRef = ref;

            this.evicted = false;

        }

        private PO expectedRef = null;

        /**
         * Test for an eviction event.
         * 
         * @exception AssertionFailedError
         *                if nothing was evicted since the last time an expected
         *                eviction reference was set.
         */
        public void assertEvicted() {

            if (!evicted) {

                fail("Expected " + expectedRef + " to have been evicted.");

            }

        }

        private boolean evicted = false;

        /**
         * Test for the expected #of eviction notices to date.
         * 
         * @param expected
         */
        public void assertEvictionCount(int expected) {

            assertEquals("evictionCount", expected, nevicted);

        }

        /**
         * The #of eviction notices to date.
         */
        public int getEvictionCount() {
            return nevicted;
        }

        private int nevicted = 0;

        /**
         * @throws AssertionFailedError
         *             if the evicted reference is not the next expected
         *             eviction reference or if no eviction is expected.
         */
        public void evicted(HardReferenceCache<PO> cache, PO ref) {

            assertNotNull("cache", cache);
            assertNotNull("ref", ref);

            if (expectedRef == null && expectedRefs.size() > 0) {

                /*
                 * There is no current expectation, but there is one on the
                 * stack, so we pop it off the stack and continue.
                 * 
                 * Note: We pop the expectation off of the stack lazily so that
                 * the unit tests have the opportunity to verify that an
                 * expected reference was evicted.
                 */
                setExpectedRef(expectedRefs.pop());

            }

            if (expectedRef == null) {

                fail("Not expecting a cache eviction: ref=" + ref);

            }

            assertEquals("ref", expectedRef, ref); // Note: This is a reference test.
            //                assertTrue("ref", expectedRef == ref);

            // Reset the expectated ref to null.
            expectedRef = null;

            // Note that the eviction occurred.
            evicted = true;

            nevicted++;

            // Delegate the actual eviction behavior.
            super.evicted(cache, ref);

        }

    }
    
    /**
     * Wraps the basic implementation and exposes a protected method that we
     * need to write the tests in this suite.
     * 
     * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
     * @version $Id$
     * @param <T>
     */
    public static class MyHardReferenceCache<T> extends HardReferenceCache<T> {

        public MyHardReferenceCache(HardReferenceCacheEvictionListener<T> listener, int capacity) {
            super(listener, capacity);
        }
        
        public MyHardReferenceCache(HardReferenceCacheEvictionListener<T> listener, int capacity, int nscan) {
            super(listener, capacity,nscan);
        }
        
        public T[] toArray() {
            
            return super.toArray();
            
        }
        
    }
}
