/*
 * Copyright 2014, Hridesh Rajan, Robert Dyer, 
 *                 and Iowa State University of Science and Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package boa.compiler.ast.types;

import java.util.ArrayList;
import java.util.List;

import boa.compiler.ast.Identifier;
import boa.compiler.ast.EnumBodyDeclaration;
import boa.compiler.visitors.AbstractVisitor;
import boa.compiler.visitors.AbstractVisitorNoArg;
import boa.compiler.visitors.AbstractVisitorNoReturn;

/**
 * 
 * @author ankuraga
 */
public class EnumType extends AbstractType {
	protected final List<EnumBodyDeclaration> members = new ArrayList<EnumBodyDeclaration>();
	
	public List<EnumBodyDeclaration> getMembers() {
		return members;
	}

	public int getMembersSize() {
		return members.size();
	}

	public EnumBodyDeclaration getMember(final int index) {
		return members.get(index);
	}

	public void addMember(final EnumBodyDeclaration c) {
		c.setParent(this);
		members.add(c);
	}

	/** {@inheritDoc} */
	@Override
	public <T,A> T accept(final AbstractVisitor<T,A> v, A arg) {
		return v.visit(this, arg);
	}

	/** {@inheritDoc} */
	@Override
	public <A> void accept(final AbstractVisitorNoReturn<A> v, A arg) {
		v.visit(this, arg);
	}

	/** {@inheritDoc} */
	@Override
	public void accept(final AbstractVisitorNoArg v) {
		v.visit(this);
	}

	public EnumType clone() {
		final EnumType t = new EnumType();
		for (final EnumBodyDeclaration c : members)
			t.addMember(c.clone());
		copyFieldsTo(t);
		return t;
	}
}