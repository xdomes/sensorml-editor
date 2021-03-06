/***************************** BEGIN LICENSE BLOCK ***************************

The contents of this file are subject to the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one
at http://mozilla.org/MPL/2.0/.

Software distributed under the License is distributed on an "AS IS" basis,
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
for the specific language governing rights and limitations under the License.
 
Copyright (C) 2012-2017 Sensia Software LLC. All Rights Reserved.
 
******************************* END LICENSE BLOCK ***************************/

package com.sensia.relaxNG;


public class RNGInvalidContent extends RNGTag
{
    private static final long serialVersionUID = 9033655978297251577L;
    
    protected RNGTag expectedPattern;
    protected Object invalidContent;
    
    
    @Override
    protected RNGTag newInstance()
    {
        return null;
    }


    @Override
    public void accept(RNGTagVisitor visitor)
    {
        visitor.visit(this);
    }


    public Object getInvalidContent()
    {
        return invalidContent;
    }


    public void setInvalidContent(Object invalidContent)
    {
        this.invalidContent = invalidContent;
    }


    public RNGTag getExpectedPattern()
    {
        return expectedPattern;
    }


    public void setExpectedPattern(RNGTag pattern)
    {
        this.expectedPattern = pattern;
    }

}
