/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Velocity", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.velocity.tools.view.context;

import org.apache.velocity.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;


/**
 * <p>Objects implementing this interface are passed to view tools
 * upon initialization by the 
 *{@link org.apache.velocity.tools.view.servlet.ServletToolboxManager}.</p> 
 * 
 * <p>The interface provides view tools in a servlet environment 
 * access to relevant context information, like servlet request, servlet 
 * context and the velocity context.</p>
 *
 * @author <a href="mailto:sidler@teamup.com">Gabe Sidler</a>
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 *
 * @version $Id: ViewContext.java,v 1.4 2003/08/02 20:42:04 nbubna Exp $ 
 */
public interface ViewContext
{
    /** Key used for the HTTP request object. */
    public static final String REQUEST = "request";

    /** Key used for the HTTP response object. */
    public static final String RESPONSE = "response";

    /** Key used for the HTTP session object. */
    public static final String SESSION = "session";

    /** Key used for the servlet context object. */
    public static final String APPLICATION = "application";

    /** Key used for XHTML setting (tells tools and macros to output XHTML). */
    public static final String XHTML = "XHTML";


    /**
     * <p>Returns the instance of {@link HttpServletRequest} for this request.</p>
     */
    public HttpServletRequest getRequest();


    /**
     * <p>Returns the instance of {@link HttpServletResponse} for this request.</p>
     */
    public HttpServletResponse getResponse();


    /**
     * <p>Returns the instance of {@link ServletContext} for this request.</p>
     */
    public ServletContext getServletContext();


    /**
     * <p>Searches for the named attribute in request, session (if valid), 
     * and application scope(s) in order and returns the value associated 
     * or null.</p>
     */
    public Object getAttribute(String key);


    /**
     * <p>Returns a reference to the current Velocity context.</p>
     */
    public Context getVelocityContext();

}
