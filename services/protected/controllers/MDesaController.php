<?php 
class MDesaController extends WRestController{

	protected $_modelName = "MDesa"; //model to be used as resource


	public function actions() //determine which of the standard actions will support the controller
	{
		return array(
			'list' => array( //use for get list of objects
				'class' => 'WRestListAction',
				/*'filterBy' => array( //this param used in `where` expression when forming an db query
					'account_id' => 'account_id', // 'name_in_table' => 'request_param_name'
				),*/
				'limit' => 'limit', //request parameter name which will contain a limit of object
				'page' => 'page', //request parameter name which will contain a requested page num
				'order' => 'order', //request parameter name which will contain ordering for sort
			),
			'delete' => 'WRestDeleteAction',
			'get' => 'WRestGetAction',
			'create' => 'WRestCreateAction', //provide 'scenario' param
			'update' => array(
				'class' => 'WRestUpdateAction',
				'scenario' => 'update' //as well as in WRestCreateAction optional param
			)
		);

	}
}

 ?>