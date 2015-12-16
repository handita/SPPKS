<?php

/**
 * This is the model class for table "m_kab".
 *
 * The followings are the available columns in table 'm_kab':
 * @property string $kode_prov
 * @property string $kode_kab
 * @property string $nama_kab
 *
 * The followings are the available model relations:
 * @property MKec[] $mKecs
 * @property MKec[] $mKecs1
 * @property MPetugas[] $mPetugases
 * @property MPetugas[] $mPetugases1
 * @property MProv $kodeProv
 */
class MK extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MK the static model class
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
		return 'm_kab';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_prov, kode_kab, nama_kab', 'required'),
			array('kode_prov, kode_kab', 'length', 'max'=>2),
			array('nama_kab', 'length', 'max'=>255),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_prov, kode_kab, nama_kab', 'safe', 'on'=>'search'),
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
			'mKecs' => array(self::HAS_MANY, 'MKec', 'kode_kab'),
			'mKecs1' => array(self::HAS_MANY, 'MKec', 'kode_prov'),
			'mPetugases' => array(self::HAS_MANY, 'MPetugas', 'kode_kab'),
			'mPetugases1' => array(self::HAS_MANY, 'MPetugas', 'kode_prov'),
			'kodeProv' => array(self::BELONGS_TO, 'MProv', 'kode_prov'),
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
			'nama_kab' => 'Nama Kab',
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
		$criteria->compare('nama_kab',$this->nama_kab,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}