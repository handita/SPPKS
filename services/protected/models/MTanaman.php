<?php

/**
 * This is the model class for table "m_tanaman".
 *
 * The followings are the available columns in table 'm_tanaman':
 * @property integer $kode_tanaman
 * @property string $nama_tanaman
 *
 * The followings are the available model relations:
 * @property MNusrt[] $mNusrts
 */
class MTanaman extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MTana the static model class
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
		return 'm_tanaman';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_tanaman', 'required'),
			array('kode_tanaman', 'numerical', 'integerOnly'=>true),
			array('nama_tanaman', 'length', 'max'=>50),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_tanaman, nama_tanaman', 'safe', 'on'=>'search'),
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
			'mNusrts' => array(self::HAS_MANY, 'MNusrt', 'kode_tanaman'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'kode_tanaman' => 'Kode Tanaman',
			'nama_tanaman' => 'Nama Tanaman',
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

		$criteria->compare('kode_tanaman',$this->kode_tanaman);
		$criteria->compare('nama_tanaman',$this->nama_tanaman,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}