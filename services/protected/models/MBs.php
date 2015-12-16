<?php

/**
 * This is the model class for table "m_bs".
 *
 * The followings are the available columns in table 'm_bs':
 * @property string $kode_prov
 * @property string $kode_kab
 * @property string $kode_kec
 * @property string $kode_desa
 * @property string $nbs
 * @property string $kode_petugas
 *
 * The followings are the available model relations:
 * @property MDesa $kodeDesa
 * @property MDesa $kodeKab
 * @property MDesa $kodeKec
 * @property MDesa $kodeProv
 * @property MPetugas $kodePetugas
 * @property MNusrt[] $mNusrts
 * @property MNusrt[] $mNusrts1
 * @property MNusrt[] $mNusrts2
 * @property MNusrt[] $mNusrts3
 * @property MNusrt[] $mNusrts4
 */
class MBs extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MBs the static model class
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
		return 'm_bs';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_prov, kode_kab, kode_kec, kode_desa, nbs', 'required'),
			array('kode_prov, kode_kab', 'length', 'max'=>2),
			array('kode_kec, kode_desa', 'length', 'max'=>3),
			array('nbs', 'length', 'max'=>4),
			array('kode_petugas', 'length', 'max'=>9),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_prov, kode_kab, kode_kec, kode_desa, nbs, kode_petugas', 'safe', 'on'=>'search'),
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
			'kodeDesa' => array(self::BELONGS_TO, 'MDesa', 'kode_desa'),
			'kodeKab' => array(self::BELONGS_TO, 'MDesa', 'kode_kab'),
			'kodeKec' => array(self::BELONGS_TO, 'MDesa', 'kode_kec'),
			'kodeProv' => array(self::BELONGS_TO, 'MDesa', 'kode_prov'),
			'kodePetugas' => array(self::BELONGS_TO, 'MPetugas', 'kode_petugas'),
			'mNusrts' => array(self::HAS_MANY, 'MNusrt', 'kode_desa'),
			'mNusrts1' => array(self::HAS_MANY, 'MNusrt', 'kode_kab'),
			'mNusrts2' => array(self::HAS_MANY, 'MNusrt', 'kode_kec'),
			'mNusrts3' => array(self::HAS_MANY, 'MNusrt', 'kode_prov'),
			'mNusrts4' => array(self::HAS_MANY, 'MNusrt', 'nbs'),
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
			'nbs' => 'Nbs',
			'kode_petugas' => 'Kode Petugas',
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
		$criteria->compare('nbs',$this->nbs,true);
		$criteria->compare('kode_petugas',$this->kode_petugas,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}