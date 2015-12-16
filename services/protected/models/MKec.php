<?php

/**
 * This is the model class for table "m_kec".
 *
 * The followings are the available columns in table 'm_kec':
 * @property string $kode_prov
 * @property string $kode_kab
 * @property string $kode_kec
 * @property string $nama_kec
 *
 * The followings are the available model relations:
 * @property MKab $kodeKab
 * @property MKab $kodeProv
 * @property MDesa[] $mDesas
 * @property MDesa[] $mDesas1
 * @property MDesa[] $mDesas2
 */
class MKec extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MKec the static model class
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
		return 'm_kec';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_prov, kode_kab, kode_kec, nama_kec', 'required'),
			array('kode_prov, kode_kab', 'length', 'max'=>2),
			array('kode_kec', 'length', 'max'=>3),
			array('nama_kec', 'length', 'max'=>255),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_prov, kode_kab, kode_kec, nama_kec', 'safe', 'on'=>'search'),
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
			'kodeKab' => array(self::BELONGS_TO, 'MKab', 'kode_kab'),
			'kodeProv' => array(self::BELONGS_TO, 'MKab', 'kode_prov'),
			'mDesas' => array(self::HAS_MANY, 'MDesa', 'kode_kab'),
			'mDesas1' => array(self::HAS_MANY, 'MDesa', 'kode_kec'),
			'mDesas2' => array(self::HAS_MANY, 'MDesa', 'kode_prov'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'kode_prov' => 'Kode Prov',
			'kode_kab' => 'Kode Kab',
			'kode_kec' => 'Kode Kec',
			'nama_kec' => 'Nama Kec',
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
		$criteria->compare('kode_kab',$this->kode_kab,true);
		$criteria->compare('kode_kec',$this->kode_kec,true);
		$criteria->compare('nama_kec',$this->nama_kec,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}