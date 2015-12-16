<?php

/**
 * This is the model class for table "m_desa".
 *
 * The followings are the available columns in table 'm_desa':
 * @property string $kode_prov
 * @property string $kode_kab
 * @property string $kode_kec
 * @property string $kode_desa
 * @property string $nama_desa
 * @property string $klasifikasi
 *
 * The followings are the available model relations:
 * @property MKec $kodeKab
 * @property MKec $kodeKec
 * @property MKec $kodeProv
 * @property MBs[] $mBs
 * @property MBs[] $mBs1
 * @property MBs[] $mBs2
 * @property MBs[] $mBs3
 */
class MDesa extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MDesa the static model class
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
		return 'm_desa';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_prov, kode_kab, kode_kec, kode_desa, nama_desa, klasifikasi', 'required'),
			array('kode_prov, kode_kab', 'length', 'max'=>2),
			array('kode_kec, kode_desa', 'length', 'max'=>3),
			array('nama_desa', 'length', 'max'=>255),
			array('klasifikasi', 'length', 'max'=>1),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_prov, kode_kab, kode_kec, kode_desa, nama_desa, klasifikasi', 'safe', 'on'=>'search'),
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
			'kodeKab' => array(self::BELONGS_TO, 'MKec', 'kode_kab'),
			'kodeKec' => array(self::BELONGS_TO, 'MKec', 'kode_kec'),
			'kodeProv' => array(self::BELONGS_TO, 'MKec', 'kode_prov'),
			'mBs' => array(self::HAS_MANY, 'MBs', 'kode_desa'),
			'mBs1' => array(self::HAS_MANY, 'MBs', 'kode_kab'),
			'mBs2' => array(self::HAS_MANY, 'MBs', 'kode_kec'),
			'mBs3' => array(self::HAS_MANY, 'MBs', 'kode_prov'),
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
			'kode_desa' => 'Kode Desa',
			'nama_desa' => 'Nama Desa',
			'klasifikasi' => 'Klasifikasi',
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
		$criteria->compare('kode_desa',$this->kode_desa,true);
		$criteria->compare('nama_desa',$this->nama_desa,true);
		$criteria->compare('klasifikasi',$this->klasifikasi,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}

}