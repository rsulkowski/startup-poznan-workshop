/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.tomtom.example.areanotifier.utils.arch

import androidx.lifecycle.MutableLiveData

/**
 * Used to hide the boilerplate code LiveData<Resource<T>>.
 * Then, you just use ResourceLiveData<T>.
 */
class ResourceLiveData<T> : MutableLiveData<Resource<T>>()

/**
 * Used to hide the boilerplate code LiveData<Resource<<ListT>>>.
 * Then, you just use ResourceLiveData<T>, and then use List<>.
 */
class ResourceListLiveData<T> : MutableLiveData<Resource<List<T>>>()