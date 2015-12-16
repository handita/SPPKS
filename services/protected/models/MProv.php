<?php

/**
 * This is the model class for table "m_prov".
 *
 * The followings are the available columns in table 'm_prov':
 * @property string $kode_prov
 * @property string $nama_prov
 *
 * The followings are the available model relations:
 * @property MKab[] $mKabs
 */
class MProv extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MProv the static model class
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
		return 'm_prov';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_prov, nama_prov', 'required'),
			array('kode_prov', 'length', 'max'=>2),
			array('nama_prov', 'length', 'max'=>255),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_prov, nama_prov', 'safe', 'on'=>'search'),
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
			'mKabs' => array(self::HAS_MANY, 'MKab', 'kode_prov'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'kode_prov' => 'Kode Prov',
			'nama_prov' => 'Nama Prov',
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

		$criteria->compare('kode_prov',$this->kode_prov,true);
		$criteria->compare('nama_prov',$this->nama_prov,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}