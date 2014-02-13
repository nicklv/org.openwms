'use strict';

/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/**
 * A RolesService provide CRUD functions for <code>Role</code> domain classes.
 *
 * @module openwms_services
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
var servicesModule = angular.module('openwms_services', ['ngResource', 'toaster']);

servicesModule.factory('rolesService',['$http', '$resource', '$q', 'toaster',
	function($http, $resource, $q, toaster) {
		return {
			/**
			 * Create a non-existing Role instance and send an POST request to the backend. If the Role with its unique identifier exists the service will reject the request.
			 *
			 * @param url The part of the URL that defines the type of entities to work with
			 * @param $scope The current scope
			 * @param entity the entity to add
			 * @returns {Promise.promise|*} A promise to evaluate: In case of success the Role is returned, otherwise the ResponseItem
			 */
			add : function(url, $scope, entity) {
				var delay = $q.defer();
				$http.defaults.headers.put['Auth-Token'] = $scope.authToken;
				$http.post($scope.rootUrl+url, entity)
					.success(function (data) {
						delay.resolve(data.items[0].obj[0]);
					})
					.error(function (data, status, e, f) {
						var err = new Error(status, data);
						err.data = {
							httpStatus: data.items[0].httpStatus,
							message: data.items[0].message
						};
						delay.reject(err);
					});
				return delay.promise;
			},
			/**
			 * Send a http DELETE request to remove selected Roles. The name of Roles to delete are appended as URL request parameter.
			 *
			 * @param url The part of the URL that defines the type of entities to work with
			 * @param $scope The current scope
			 * @returns {Promise.promise|*} A promise to evaluate: In case of success nothing is returned, otherwise the ResponseItem
			 */
			delete : function(url, $scope) {
				var delay = $q.defer();
				$http.defaults.headers.put['Auth-Token'] = $scope.authToken;
				$http.delete($scope.rootUrl+url)
					.success(function () {
						delay.resolve();
					})
					.error(function (d, s, e, f) {
						delay.reject(new Error(s, f));
					});
				return delay.promise;
			},
			/**
			 * Send a http PUT request to save a Role. The Role to save must already exist.
			 *
			 * @param url The part of the URL that defines the type of entities to work with
			 * @param $scope The current scope
			 * @param entity The existing Entity to update
			 * @returns {Promise.promise|*} A promise to evaluate: In case of success the updated Role is returned, otherwise the ResponseItem
			 */
			save : function (url, $scope, entity) {
				var delay = $q.defer();
				$http.defaults.headers.put['Auth-Token'] = $scope.authToken;
				$http.defaults.headers.common['Content-Type'] = 'application/json';
				$http.put($scope.rootUrl+url, entity)
					.success(function (savedEntity) {
						delay.resolve(savedEntity);
					})
					.error(function (d, s, e, f) {
						delay.reject(new Error(s, f));
					});
				return delay.promise;
			},
			/**
			 * Send a http GET request to load and retrieve all existing Roles from the backend.
			 *
			 * @param $scope The current scope
			 * @returns {Promise.promise|*} A promise to evaluate: In case of success all retrieved Roles are returned, otherwise the ResponseItem
			 */
			getAll : function($scope) {
				var delay = $q.defer();
				$http.defaults.headers.common['Auth-Token'] = $scope.authToken;
				$http.get($scope.rootUrl+'/roles')
					.success(function (data) {
						delay.resolve(data.items[0].obj[0]);
					})
					.error(function (d, s, e, f) {
						delay.reject(new Error(s, f));
					});
				return delay.promise;
			}
		}
	}
]);