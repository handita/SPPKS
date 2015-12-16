<?php

/**
 * This is the model class for table "t_bulan_kues".
 *
 * The followings are the available columns in table 't_bulan_kues':
 * @property string $id_ruta
 * @property integer $bulan
 * @property string $autonum
 * @property string $variabel
 * @property string $nilai
 * @property string $is_used
 */
class TBulanKues extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return TBulanKues the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}

	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return 't_bulan_kues';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('id_ruta, bulan, variabel', 'required'),
			array('bulan', 'numerical', 'integerOnly'=>true),
			array('id_ruta', 'length', 'max'=>19),
			array('variabel', 'length', 'max'=>255),
			array('is_used', 'length', 'max'=>1),
			array('nilai', 'safe'),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('id_ruta, bulan, autonum, variabel, nilai, is_used', 'safe', 'on'=>'search'),
		);
	}

	/**
	 * @return array relational rules.
	 */
	public function relations()
	{
		// NOTE: you may need to adjust the relation name and the related
		// class name for the relations automatically generated below.
		return array(
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'id_ruta' => 'Id Ruta',
			'bulan' => 'Bulan',
			'autonum' => 'Autonum',
			'variabel' => 'Variabel',
			'nilai' => 'Nilai',
			'is_used' => 'Is Used',
		);
	}

	/**
	 * Retrieves a list of models based on the current search/filter conditions.
	 * @return CActiveDataProvider the data provider that can return the models based on the search/filter conditions.
	 */
	public function search()
	{
		// Warning: Please modify the following code to remove attributes that
		// should not be searched.

		$criteria=new CDbCriteria;

		$criteria->compare('id_ruta',$this->id_ruta,true);
		$criteria->compare('bulan',$this->bulan);
		$criteria->compare('autonum',$this->autonum,true);
		$criteria->compare('variabel',$this->variabel,true);
		$criteria->compare('nilai',$this->nilai,true);
		$criteria->compare('is_used',$this->is_used,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}