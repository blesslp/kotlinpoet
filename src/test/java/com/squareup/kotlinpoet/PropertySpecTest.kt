/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.kotlinpoet

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PropertySpecTest {
  @Test fun nullable() {
    val type = TypeName.get(String::class).asNullable()
    val a = PropertySpec.builder(type, "foo").build()
    assertThat(a.toString()).isEqualTo("val foo: java.lang.String?\n")
  }

  @Test fun delegated() {
    val prop = PropertySpec.builder(String::class, "foo")
        .delegate("Delegates.notNull()")
        .build()
    assertThat(prop.toString()).isEqualTo("val foo: java.lang.String by Delegates.notNull()\n")
  }

  @Test fun equalsAndHashCode() {
    val type = Int::class
    var a = PropertySpec.builder(type, "foo").build()
    var b = PropertySpec.builder(type, "foo").build()
    assertThat(a == b).isTrue()
    assertThat(a.hashCode()).isEqualTo(b.hashCode())
    a = PropertySpec.builder(type, "FOO", KModifier.PUBLIC, KModifier.LATEINIT).build()
    b = PropertySpec.builder(type, "FOO", KModifier.PUBLIC, KModifier.LATEINIT).build()
    assertThat(a == b).isTrue()
    assertThat(a.hashCode()).isEqualTo(b.hashCode())
  }
}
